package ch.hoene.perzist.framework.creator;



public interface Creator<RESULT, SOURCE>
{
	RESULT create(SOURCE source);
}