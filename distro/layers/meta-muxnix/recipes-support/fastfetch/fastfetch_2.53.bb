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

# Specify the source URI and its checksum for validation.
SRC_URI = "git://github.com/fastfetch-cli/fastfetch;protocol=https;branch=master"
SRCREV = "4098cf51b35c19a102e5df51f5e14d6b80902404"

# Inherit the cmake class for building the project.
inherit cmake

do_install(){
    install -d ${D}${bindir}
    install -m 0755 ${B}/fastfetch ${D}${bindir}
}
