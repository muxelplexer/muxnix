FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
COMPATIBLE_MACHINE:luckfox-omni3576 = "luckfox-omni3576"
SRC_URI += "file://enable-ffs.cfg \
            file://0001-dts-rockchip-rk3576-luckfox-core3576-set-output-to-U.patch \
            "
