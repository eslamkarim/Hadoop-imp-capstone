
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public class TextArrayWritable extends ArrayWritable implements WritableComparable {
    public TextArrayWritable() {
        super(Text.class);
    }

    public TextArrayWritable(String[] strings) {
        super(Text.class);
        Text[] texts = new Text[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            texts[i] = new Text(strings[i]);
        }
        set(texts);
    }

    @Override
    public String toString() {
        return String.join(",", toStrings());
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}
