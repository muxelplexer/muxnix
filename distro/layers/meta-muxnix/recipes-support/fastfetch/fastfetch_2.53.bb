DESCRIPTION = "Fastfetch is a neofetch-like tool for fetching system information and displaying them in a pretty way.\
                It is written mainly in C, with performance and customizability in mind. \
                "
SUMMARY =   "System Information. \
            This recipe is intended to take FASTFETCH tool - as is - to YOCTO projects.\
            "
SECTION = "console/utils"

HOMEPAGE = "https://github.com/fastfetch-cli/fastfetch"
BUGTRACKER = "https://github.com/fastfetch-cli/fastfetch/issues"

# Specify the license for the package.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit cmake
inherit externalsrc

EXTERNALSRC = "${MUXNIX_APP_SOURCES}/fastfetch"
EXTERNALSRC_BUILD = "${WORKDIR}/${BPN}-${PV}"

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${B}/fastfetch ${D}${bindir}
}
