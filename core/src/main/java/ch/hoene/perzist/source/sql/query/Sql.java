package ch.hoene.perzist.source.sql.query;

import ch.hoene.perzist.source.relational.Field;

/**
 * @author Simon Honegger (Hoene84)
 *
 * Marker Interface for a complete, executable Sql
 */

public interface Sql extends SqlPart
{
    public static final Field FIELD_PSEUDO_STAR = new Field("*");
}
