package ch.hoene.perzist.source.relational.fieldtype;



/**
 * @param <R> What the Visitor Produces (can be {@link Void} if nothing gets produced)
 */
public interface FieldVisitor<R, RESULT>
{
    R visit(FieldPersistableAsString<? super RESULT> field);

    R visit(FieldPersistableAsDouble<? super RESULT> field);

    R visit(FieldPersistableAsInteger<? super RESULT> field);

}
