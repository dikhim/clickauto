package org.dikhim.clickauto.jsengine.objects.generators;


import org.dikhim.clickauto.jsengine.objects.CombinedObject;

public class CombinedObjectCodeGenerator extends SimpleCodeGenerator implements CombinedObject {
    public CombinedObjectCodeGenerator(int lineSize) {
        super("combined", lineSize, CombinedObject.class);
    }

    public CombinedObjectCodeGenerator() {
        super("combined", CombinedObject.class);
    }

    @Override
    public void run(String encoding, String code) {
        buildStringForCurrentMethod(encoding, code);
    }
}