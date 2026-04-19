# muxnix Agents Instructions

## Overview

- This is a yocto environment. To able to run `bitbake`, `init-build-env` must be sourced.

## Directories

- `build/`         - contains the Yocto outputs and may never be searched.
- `distro/bitbake` - contains the bitbake sources for this version of Yocto.
- `distro/layers`  - contains all layers used for the Yocto build.
