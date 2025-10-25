SRCREV = "8c42f534d7e1956192ef8457fae884469f60ff13"
SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master \
           file://0001-config-adapt-to-muxnix-needs.patch \
           file://0002-config-rk3576-switch-to-upstream-luckfox-dt.patch \
           file://fragment.cfg \
           file://usb.cfg \
           file://logging.cfg \
           "
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

