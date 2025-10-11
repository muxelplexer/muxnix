# DEPENDS:append:rk3399 = " gcc-arm-none-eabi-native"

COMPATIBLE_MACHINE:append:rk3576 = "|rk3576"

# # code bloats with clang and results in error below now
# # | aarch64-yoe-linux-musl-ld: region `PMUSRAM' overflowed by 3928 bytes
# # this needs fixing until then use gcc
# TOOLCHAIN:rk3399 = "gcc"

# This is not a typo, rk3566 and rk3568 are supported by the same code base.
fixup_baudrate:rk3576() {
	sed -i "s/#define FPGA_BAUDRATE\s\+.*/#define FPGA_BAUDRATE ${RK_CONSOLE_BAUD}/" ${S}/plat/rockchip/rk3576/rk3576_def.h
}

