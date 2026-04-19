SUMMARY = "Muxnix Linux Kernel"
#     COMPATIBLE_MACHINE:yourmachine = "yourmachine"

#   defconfig: When a defconfig is provided, the linux-yocto configuration
#              uses the filename as a trigger to use a 'allnoconfig' baseline
#              before merging the defconfig into the build. 
#
#              If the defconfig file was created with make_savedefconfig, 
#              not all options are specified, and should be restored with their
#              defaults, not set to 'n'. To properly expand a defconfig like
#              this, specify: KCONFIG_MODE="--alldefconfig" in the kernel
#              recipe.
inherit kernel
require recipes-kernel/linux/linux-yocto.inc
inherit externalsrc

EXTERNALSRC = "${MUXNIX_SYS_SOURCES}/linux"
EXTERNALSRC_BUILD = "${WORKDIR}/${BPN}-${PV}"

SRC_URI = "file://panfrost-drm.cfg \
           "
KERNEL_FEATURES:remove = "bsp/rockchip/remove-non-rockchip-arch-arm64.scc"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"


KBUILD_DEFCONFIG = "luckfox_omni3576_defconfig"
KCONFIG_MODE = "--alldefconfig"
do_kernel_configcheck[noexec] = "1"


LINUX_VERSION ?= "6.19.3"
LINUX_VERSION_EXTENSION:append = "-muxnix"

# SRCREV is determined by the git submodule checkout

PV = "${LINUX_VERSION}"
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE:luckfox-omni3576 = "luckfox-omni3576"
