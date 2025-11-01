FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " file://00-persistent-journal.conf"
PACKAGECONFIG:remove = " randomseed nss-mymachines machined backlight"
PACKAGECONFIG:append = " coredump hostnamed openssl rfkill repart"

do_install:append() {
    install -d ${D}/${systemd_unitdir}/journald.conf.d
    install -m 0644 ${UNPACKDIR}/00-persistent-journal.conf ${D}/${systemd_unitdir}/journald.conf.d
}
