SUMMARY = "Kernel driver module for the AIC8800 WiFi Chip-Set"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-3.0-only"

inherit module pkgconfig

SRC_URI = "git://github.com/muxelplexer/aic8800;protocol=https;branch=yocto_build \
           file://aic8800_bsp.conf \
           file://aic8800_fdrv.conf \
           "
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
SRCREV = "5c9041cc62954433169c633b98e5b1aa0af0d4ad"

S = "${UNPACKDIR}/${PN}-${PV}"
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
