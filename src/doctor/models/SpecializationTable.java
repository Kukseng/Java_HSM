package doctor.models;

import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle;

public class SpecializationTable {
    public void specializationGenerator() {
        final String BLUE = "\u001B[34m";
        final String BOLD_BLUE = "\u001B[35m";
        final String RESET = "\u001B[0m";
        final String BRIGHT_GREEN = "\033[92m";

        int consoleWidth = 180;

        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 40, 90);

        table.addCell(BOLD_BLUE + "DOCTOR SPECIALIZATION" + RESET, centerStyle);

        table.addCell(BRIGHT_GREEN + "CARDIOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "DERMATOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "ENDOCRINOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "GASTROENTEROLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "GENERAL_MEDICINE" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "NEUROLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "OBSTETRICS_GYNECOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "ONCOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "OPHTHALMOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "ORTHOPEDICS" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "PEDIATRICS" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "PSYCHIATRY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "PULMONOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "RADIOLOGY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "SURGERY" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "UROLOGY" + RESET, centerStyle);

        int tableWidth = 100;
        int leftPadding = (consoleWidth - tableWidth) / 2;
        String padding = " ".repeat(leftPadding);

        String[] tableLines = table.render().split("\n");

        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }
    }
}