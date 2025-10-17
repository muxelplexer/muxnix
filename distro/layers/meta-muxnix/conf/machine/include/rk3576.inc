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

KBUILD_DEFCONFIG  ?= "defconfig"
KERNEL_FEATURES:append:rk3576 = " bsp/rockchip/remove-non-rockchip-arch-arm64.scc"
UBOOT_MACHINE     ?= "generic-rk3576_defconfig"

UBOOT_SUFFIX ?= "itb"
UBOOT_ENTRYPOINT ?= "0x48000000"

TFA_PLATFORM = "rk3576"
TFA_BUILD_TARGET = "bl31"

ENABLE_STATELESS_VPU_GST ?= "1"
