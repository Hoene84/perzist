package ch.hoene.perzist.source.relational;


import ch.hoene.perzist.access.sort.SortOrder;

public class FieldSortOrder<RESULT, TYPE> {

    private final FieldPersistable<RESULT, TYPE> field;
    private final SortOrder sortOrder;

    public FieldSortOrder(FieldPersistable<RESULT, TYPE> field, SortOrder sortOrder) {
        this.field = field;
        this.sortOrder = sortOrder;
    }

    public FieldPersistable<RESULT, TYPE> getField() {
        return field;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }
}
