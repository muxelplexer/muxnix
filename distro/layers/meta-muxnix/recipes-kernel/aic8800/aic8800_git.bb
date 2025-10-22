SUMMARY = "Kernel driver module for the AIC8800 WiFi Chip-Set"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-3.0-only"

inherit module pkgconfig

SRC_URI = "git://github.com/muxelplexer/aic8800;protocol=https;branch=yocto_build"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
SRCREV = "5c9041cc62954433169c633b98e5b1aa0af0d4ad"

S = "${UNPACKDIR}/${PN}-${PV}"

EXTRA_OEMAKE:append = "\
    CONFIG_PREALLOC_RX_SKB=y \
    CONFIG_PREALLOC_TXQ=y \
    CONFIG_RESV_MEM_SUPPORT=y \
    CONFIG_PLATFORM_UBUNTU=TRUE \
    -C ${STAGING_KERNEL_BUILDDIR} M=${S}/src/SDIO/driver_fw/driver/aic8800 \
"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPDEPENDS:${PN} = "aic8800-firmware"
RPROVIDES:${PN} += "kernel-module-aic8800"
