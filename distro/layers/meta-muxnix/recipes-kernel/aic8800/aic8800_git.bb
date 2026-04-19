SUMMARY = "Kernel driver module for the AIC8800 WiFi Chip-Set"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-3.0-only"

inherit module pkgconfig
inherit externalsrc

EXTERNALSRC = "${MUXNIX_SYS_SOURCES}/aic8800"
EXTERNALSRC_BUILD = "${WORKDIR}/${BPN}-${PV}"

SRC_URI = "file://aic8800_bsp.conf \
           file://aic8800_fdrv.conf \
           "
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
MODULES_MODULE_SYMVERS_LOCATION = "src/SDIO/driver_fw/driver/aic8800"

EXTRA_OEMAKE:append = "\
    CONFIG_PREALLOC_RX_SKB=y \
    CONFIG_PREALLOC_TXQ=y \
    CONFIG_RESV_MEM_SUPPORT=y \
    CONFIG_PLATFORM_UBUNTU=TRUE \
    -C ${STAGING_KERNEL_BUILDDIR} M=${S}/src/SDIO/driver_fw/driver/aic8800 \
"

do_install:append() {
    install -d ${D}/${sysconfdir}/modprobe.d
    install -m 644 ${UNPACKDIR}/aic8800_bsp.conf ${D}/${sysconfdir}/modprobe.d/
    install -m 644 ${UNPACKDIR}/aic8800_fdrv.conf ${D}/${sysconfdir}/modprobe.d/
}

FILES:${PN}:append = "${sysconfdir}/modprobe.d/*"
RPDEPENDS:${PN} = "aic8800-firmware"
RPROVIDES:${PN} += "kernel-module-aic8800"
