package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import java.util.Map;

public class ReportGeneratorImpl implements ReportGenerator {
    private static final String HEADER = "fruit,quantity";

    @Override
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append(HEADER).append(System.lineSeparator());

        Map<String, Integer> fruits = Storage.getAllFruits();

        for (Map.Entry<String, Integer> entry : fruits.entrySet()) {
            report.append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }

        return report.toString();
    }
}
