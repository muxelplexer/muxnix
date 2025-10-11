SOC_FAMILY ?= "rk3576"
MACHINEOVERRIDES =. "rk3576:"
SOC_FAMILY = "rk3576"
DEFAULTTUNE ?= "cortexa72-cortexa53"
ROCKCHIP_CLOSED_TPL ?= "1"

require conf/machine/include/soc-family.inc
require conf/machine/include/rockchip-defaults.inc
require conf/machine/include/arm/armv8a/tune-cortexa72-cortexa53.inc
require conf/machine/include/rockchip-wic.inc

PREFERRED_PROVIDER_trusted-firmware-a = "rockchip-rkbin-tf-a"
PREFERRED_PROVIDER_optee-os = "rockchip-rkbin-optee-os"

KBUILD_DEFCONFIG ?= "defconfig"
KERNEL_DEVICETREE ?= "rockchip/rk3576-luckfox-omni3576.dtb"
UBOOT_MACHINE ?= "generic-rk3576_defconfig"

KERNEL_FEATURES:append:rk3576 = " bsp/rockchip/remove-non-rockchip-arch-arm64.scc"

# This board doesn't support the combined idbloader, so resort to the older
# image class
IMAGE_FSTYPES += "rockchip-gpt-img"
IMAGE_CLASSES += "rockchip-gpt-img"

UBOOT_SUFFIX ?= "bin"

ENABLE_STATELESS_VPU_GST ?= "1"
