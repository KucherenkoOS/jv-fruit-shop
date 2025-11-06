package core.basesyntax.io;

import core.basesyntax.model.FruitTransaction;
import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {

    private static final String SEPARATOR = ",";

    @Override
    public List<FruitTransaction> convertToTransaction(List<String> lines) {
        List<FruitTransaction> transactions = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(SEPARATOR);
            if (parts.length != 3) {
                throw new RuntimeException("Invalid CSV line: " + line);
            }

            String operationCode = parts[0].trim();
            String fruit = parts[1].trim();
            int quantity;

            try {
                quantity = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid quantity in line: " + line, e);
            }

            FruitTransaction.Operation operation = getOperationByCode(operationCode);

            FruitTransaction transaction = new FruitTransaction(operation, fruit, quantity);
            transactions.add(transaction);
        }
        return transactions;
    }

    private FruitTransaction.Operation getOperationByCode(String code) {
        for (FruitTransaction.Operation op : FruitTransaction.Operation.values()) {
            if (op.getCode().equals(code)) {
                return op;
            }
        }
        throw new RuntimeException("Unknown operation code: " + code);
    }
}

