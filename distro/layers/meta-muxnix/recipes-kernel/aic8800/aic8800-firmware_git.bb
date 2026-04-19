SUMMARY = "Firmware for the AIC8800 WiFi Chip-Set"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-3.0-only"

inherit externalsrc

EXTERNALSRC = "${MUXNIX_SYS_SOURCES}/aic8800"
EXTERNALSRC_BUILD = "${WORKDIR}/${BPN}-${PV}"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
# Can be any of:
#  - aic8800
#  - aic8800D80
#  - aic8800D80X2
#  - aic8800DC
CHIPSET ?= "aic8800D80"
do_compile[noexec] = "1"
do_install() {
    install -d ${D}/${nonarch_base_libdir}/firmware/aic8800_fw/SDIO/${CHIPSET}
    install -m 755 ${S}/src/SDIO/driver_fw/fw/${CHIPSET}/*.bin ${D}/${nonarch_base_libdir}/firmware/aic8800_fw/SDIO/${CHIPSET}
    install -m 644 ${S}/src/SDIO/driver_fw/fw/${CHIPSET}/*.txt ${D}/${nonarch_base_libdir}/firmware/aic8800_fw/SDIO/${CHIPSET}
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/aic8800_fw/SDIO/${CHIPSET}/*"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
RPROVIDES:${PN} = "aic8800-firmware"
