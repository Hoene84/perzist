package ch.hoene.perzist.access.creator;



public interface Creator<RESULT, SOURCE>
{
    RESULT create(SOURCE source);
}