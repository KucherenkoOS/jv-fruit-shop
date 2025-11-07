package core.basesyntax.strategy;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.db.Storage;

public class SupplyOperation implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction) {
        if (transaction == null || transaction.getFruit() == null) {
            throw new IllegalArgumentException("Transaction or fruit cannot be null");
        }

        int quantity = transaction.getQuantity();
        if (quantity <= 0) {
            throw new IllegalArgumentException("Supply quantity must be positive");
        }

        String fruit = transaction.getFruit();
        int currentQuantity = Storage.getFruitQuantity(fruit);
        int newQuantity = currentQuantity + quantity;

        Storage.setFruitQuantity(fruit, newQuantity);
    }
}


