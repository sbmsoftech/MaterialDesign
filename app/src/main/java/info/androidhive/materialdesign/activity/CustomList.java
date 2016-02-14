package info.androidhive.materialdesign.activity;

        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import info.androidhive.materialdesign.R;

/**
 * Created by Belal on 9/22/2015.
 */

public class CustomList extends ArrayAdapter<String> {

    private String[] names;
    private Activity context;

    public CustomList(Activity context, String[] names) {
        super(context, R.layout.item_layout);

        this.context = context;

        this.names = names;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.item_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.txtName);
        textViewName.setText(names[position]);
        return listViewItem;
    }
}