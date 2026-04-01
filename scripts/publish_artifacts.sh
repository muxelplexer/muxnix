#!/bin/sh
set -eu

if [ -z "${FORGEJO_TOKEN:-}" ]; then
    echo "ERROR: FORGEJO_TOKEN is not set"
    exit 1
fi

VERSION="${CI_COMMIT_BRANCH}-${CI_COMMIT_SHA:0:8}"
FORGEJO_URL="${FORGEJO_URL:-http://git:3001}"
OWNER="muxnix"
PKG_NAME="muxnix"
REPO="muxnix"

echo "=== Publishing artifacts for version: $VERSION"
echo "=== Forgejo URL: $FORGEJO_URL"
ls -lh /workdir/muxnix/artifacts/

for file in /workdir/muxnix/artifacts/*; do
    filename=$(basename "$file")
    echo "Uploading $filename → version $VERSION"
    curl --fail -X PUT \
        "${FORGEJO_URL}/api/packages/${OWNER}/generic/${PKG_NAME}/${VERSION}/${filename}" \
        -H "Authorization: token ${FORGEJO_TOKEN}" \
        -T "$file"
    echo ""
done

echo "=== Linking package to repository ${OWNER}/${REPO}"
HTTP_CODE=$(curl -o /dev/null -w '%{http_code}' -X POST \
    "${FORGEJO_URL}/api/v1/packages/${OWNER}/generic/${PKG_NAME}/-/link/${REPO}" \
    -H "Authorization: token ${FORGEJO_TOKEN}")
if [ "$HTTP_CODE" = "201" ] || [ "$HTTP_CODE" = "409" ]; then
    echo "Package linked (HTTP $HTTP_CODE)"
else
    echo "WARNING: Failed to link package (HTTP $HTTP_CODE), may already be linked"
fi
echo ""

echo ""
echo "=== Download artifacts at:"
echo "https://git.muxel.dev/api/packages/${OWNER}/generic/${PKG_NAME}/${VERSION}/"
