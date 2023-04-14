module de.haevn.utils.io {
    requires java.desktop;

    requires zip4j;
    requires org.apache.pdfbox;

    requires de.haevn.utils.datastructures;
    requires de.haevn.utils.enumeration;
    requires de.haevn.utils.annotations;
    requires de.haevn.utils.logger;
    requires de.haevn.utils.concurrency;
    requires de.haevn.utils.utils;
    requires de.haevn.utils.exceptions;

    exports de.haevn.utils.io;
    exports de.haevn.utils.io.merge;
}