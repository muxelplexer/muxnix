#!/bin/sh
set -eu

# Publish main-branch builds as a Forgejo pre-release.
# Creates/updates a rolling "latest-main" release with all artifacts.

if [ -z "${FORGEJO_TOKEN:-}" ]; then
    echo "ERROR: FORGEJO_TOKEN is not set"
    exit 1
fi

FORGEJO_URL="${FORGEJO_URL:-http://git:3001}"
OWNER="muxnix"
REPO="muxnix"
TAG="latest-main"
TITLE="build-${CI_COMMIT_SHA:0:8}"
NOTE="Automated CI build from main (${CI_COMMIT_SHA:0:8})"

API="${FORGEJO_URL}/api/v1/repos/${OWNER}/${REPO}/releases"

echo "=== Publishing main build as pre-release: ${TAG}"

# Delete existing release + assets if it exists (rolling release)
RELEASE_ID=$(curl -sf -H "Authorization: token ${FORGEJO_TOKEN}" \
    "${API}/tags/${TAG}" | grep -o '"id":[0-9]*' | head -1 | cut -d: -f2 || true)

if [ -n "${RELEASE_ID}" ]; then
    echo "Deleting previous release (id=${RELEASE_ID})"
    curl -sf -X DELETE -H "Authorization: token ${FORGEJO_TOKEN}" "${API}/${RELEASE_ID}" || true
fi

# Create new pre-release
echo "Creating pre-release ${TAG}..."
RESPONSE=$(curl -sf -X POST \
    -H "Authorization: token ${FORGEJO_TOKEN}" \
    -H "Content-Type: application/json" \
    -d "{\"tag_name\":\"${TAG}\",\"target_commitish\":\"main\",\"name\":\"${TITLE}\",\"body\":\"${NOTE}\",\"prerelease\":true}" \
    "${API}")

RELEASE_ID=$(echo "$RESPONSE" | grep -o '"id":[0-9]*' | head -1 | cut -d: -f2)
echo "Created release id=${RELEASE_ID}"

# Upload artifacts
for file in /workdir/muxnix/artifacts/*; do
    filename=$(basename "$file")
    echo "Uploading ${filename}..."
    curl --fail -X POST \
        -H "Authorization: token ${FORGEJO_TOKEN}" \
        -F "attachment=@${file}" \
        "${API}/${RELEASE_ID}/assets?name=${filename}"
    echo ""
done

echo "=== Done. Release available at:"
echo "${FORGEJO_URL}/${OWNER}/${REPO}/releases/tag/${TAG}"
