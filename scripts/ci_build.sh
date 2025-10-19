#!/usr/bin/bash

function build_image()
{
    echo -n "=== Building Yocto Image"
    export MACHINE=luckfox-omni3576
    export BITBAKEDIR=$(realpath distro/bitbake)
    export OEROOT="$(realpath distro/layers/openembedded-core)"
    export LAYER_ROOT="$(realpath distro/layers)"
    export BB_ENV_PASSTHROUGH="$BB_ENV_PASSTHROUGH PATH MACHINE BITBAKEDIR OEROOT LAYER_ROOT"
    source ./distro/layers/openembedded-core/oe-init-build-env
    bitbake core-image-minimal
}

build_image
