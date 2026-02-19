DESCRIPTION = "Different utilities from Android - corressponding configuration files for using ConfigFS with muxnix flavour"
SECTION = "console/utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

S = "${UNPACKDIR}"

SRC_URI = " \
    file://android-gadget-setup \
    file://android-gadget-start \
    file://android-gadget-cleanup \
    file://10-adbd-muxnix.conf \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/android-gadget-setup ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/android-gadget-start ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/android-gadget-cleanup ${D}${bindir}

    if [ -r ${UNPACKDIR}/android-gadget-setup.machine ] ; then
        install -d ${D}${sysconfdir}
        install -m 0644 ${UNPACKDIR}/android-gadget-setup.machine ${D}${sysconfdir}
    fi

    install -d ${D}${systemd_unitdir}/system/android-tools-adbd.service.d
    install -m 0644 ${UNPACKDIR}/10-adbd-muxnix.conf ${D}${systemd_unitdir}/system/android-tools-adbd.service.d
    install -d ${D}/etc
    touch ${D}/etc/usb-debugging-enabled
}

FILES:${PN} += " \
    ${systemd_unitdir}/system/ \
    /etc/usb-debugging-enabled \
"

PROVIDES += "android-tools-conf"
RPROVIDES:${PN} = "android-tools-conf"
BBCLASSEXTEND = "native"
