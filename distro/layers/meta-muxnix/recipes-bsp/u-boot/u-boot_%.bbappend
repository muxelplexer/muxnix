SRCREV = "8c42f534d7e1956192ef8457fae884469f60ff13"
SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master \
           file://0001-dts-rk3576-change-generic-dts-to-genetic-u-boot.patch \
           file://0002-dts-test-changes.patch \
           file://0003-dts-rk3576-actually-use-luckfox-dtb.patch \
           "
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

