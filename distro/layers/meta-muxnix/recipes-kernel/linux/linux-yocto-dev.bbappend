FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
COMPATIBLE_MACHINE:luckfox-omni3576 = "luckfox-omni3576"
SRC_URI += "file://muxnix.cfg \
            file://fragment.cfg \
            file://0001-dts-luckfox-omni3576-port-wifi-from-downstream.patch \
            file://0002-dts-luckfox-omni3576-remove-sqe-support-from-mmc.patch \
            "
