package ch.hoene.perzist.source.sql.query;

import java.util.ArrayList;
import java.util.List;

/**
 * TURTLE PLAYER
 * <p/>
 * Licensed under MIT & GPL
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * <p/>
 * More Information @ www.turtle-player.co.uk
 *
 * @author Simon Honegger (Hoene84)
 */

public class MultiValue implements SqlPart {
    final List<Object> values;

    public MultiValue(final List<Object> values) {
        this.values = values;
    }

    @Override
    public List<Object> getParams() {
        return values;
    }

    @Override
    public String toSql() {

        List<Value<Object>> sqlValues = new ArrayList<Value<Object>>();
        for(Object value : values)
        {
            sqlValues.add(new Value<Object>(value));
        }

        return " ( " + Helper.getSeparatedList(",", sqlValues.toArray(new Value<?>[sqlValues.size()])) + " ) ";
    }
}
