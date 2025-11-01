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

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://github.com/muxelplexer/linux.git;protocol=https;nocheckout=1;branch=muxnix"
KERNEL_FEATURES:remove = "bsp/rockchip/remove-non-rockchip-arch-arm64.scc"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"


KBUILD_DEFCONFIG = "luckfox_omni3576_defconfig"
KCONFIG_MODE = "--alldefconfig"
do_kernel_configcheck[noexec] = "1"


LINUX_VERSION ?= "6.18"
LINUX_VERSION_EXTENSION:append = "-muxnix"

# Modify SRCREV to a different commit hash in a copy of this recipe to
# build a different release of the Linux kernel.
# tag: v4.2 64291f7db5bd8150a74ad2036f1037e6a0428df2
SRCREV = "940b8a65dc9e6f0b96263f8e869add308852b8b2"

PV = "${LINUX_VERSION}-rc2"
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE:luckfox-omni3576 = "luckfox-omni3576"
