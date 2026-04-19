#!/usr/bin/bash

function build_image()
{
    echo "=== Building Yocto Image"
    export MACHINE=luckfox-omni3576
    export BITBAKEDIR=$(realpath distro/bitbake)
    export OEROOT="$(realpath distro/layers/openembedded-core)"
    export LAYER_ROOT="$(realpath distro/layers)"
    export BB_ENV_PASSTHROUGH="$BB_ENV_PASSTHROUGH PATH MACHINE BITBAKEDIR OEROOT LAYER_ROOT DL_DIR SSTATE_DIR"
    export DL_DIR="/workdir/muxnix/build/downloads"
    export SSTATE_DIR="/workdir/muxnix/build/sstate-cache"
    source ./distro/layers/openembedded-core/oe-init-build-env

    # CI-specific overrides only – the distro layer (muxnix.conf) already sets
    # BB_SIGNATURE_HANDLER, BB_HASHSERVE_UPSTREAM and SSTATE_MIRRORS.
    # We only override the hash server to the network instance that lives
    # inside the CI network and point it at the same upstream so hashes
    # stay synchronised with the public sstate mirror.
    cat >> conf/local.conf << 'EOF'
BB_HASHSERVE = "ws://10.90.0.1:8687"
BB_HASHSERVE_UPSTREAM = "wss://sstate.muxel.dev/hashserv"
EOF

    bitbake core-image-minimal-dev
}

build_image
