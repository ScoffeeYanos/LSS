package org.example;

import java.awt.*;

public class MissingMission extends Throwable {
    public MissingMission(String missionId) {
        System.err.println("MissingMissionID: "+missionId);
        System.err.println("----------------------------");
        System.out.println("package org.example.missionType;");
        System.out.println("");
        System.out.println("import org.example.Mission;");
        System.out.println("");
        System.out.println("public class Mission"+missionId+" extends Mission {");
        System.out.println("    public Mission"+missionId+"(){");
        System.out.println("        missionId = "+missionId+";");
        System.out.println("        name = \"\";");
        System.out.println("        LF = 1;");
        System.out.println("    }");
        System.out.println("}");
        System.err.println("----------------------------");
    }
}
