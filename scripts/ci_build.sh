#!/usr/bin/bash

function build_image()
{
    echo -n "=== Building Yocto Image"
    export MACHINE=luckfox-omni3576
    export BITBAKEDIR=$(realpath distro/bitbake)
    export OEROOT="$(realpath distro/layers/openembedded-core)"
    export LAYER_ROOT="$(realpath distro/layers)"
    export BB_ENV_PASSTHROUGH="$BB_ENV_PASSTHROUGH PATH MACHINE BITBAKEDIR OEROOT LAYER_ROOT DL_DIR SSTATE_DIR"
    export DL_DIR="/workdir/muxnix/build/downloads"
    export SSTATE_DIR="/workdir/muxnix/build/sstate-cache"
    source ./distro/layers/openembedded-core/oe-init-build-env
    cat >> conf/local.conf << 'EOF'
BB_HASHSERVE = ""
BB_SIGNATURE_HANDLER = "OEBasicHash"
SSTATE_MIRRORS += "file://.* https://sstate.muxel.dev/PATH;downloadfilename=PATH"
EOF
    bitbake core-image-minimal
}

build_image
