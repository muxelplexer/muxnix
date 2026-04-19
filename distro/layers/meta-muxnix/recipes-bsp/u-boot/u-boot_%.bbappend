inherit externalsrc
EXTERNALSRC = "${MUXNIX_SYS_SOURCES}/u-boot"
EXTERNALSRC_BUILD = "${WORKDIR}/${BPN}-${PV}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

