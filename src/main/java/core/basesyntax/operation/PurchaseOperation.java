package core.basesyntax.operation;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.storage.Storage;

public class PurchaseOperation implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction) {
        Storage.fruits.merge(
                transaction.getFruit(),
                -transaction.getQuantity(),
                Integer::sum
        );
    }
}
