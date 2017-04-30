package codes.nukki.scan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class AttendeeListAdapter extends BaseAdapter{
    private final ArrayList StudentData;
    Context context;

    public AttendeeListAdapter(Context context, Map<String, Student> map) {
        this.context = context;
        StudentData = new ArrayList();
        StudentData.addAll(map.entrySet());
    }
    @Override
    public int getCount(){
        return StudentData.size();
    }

    @Override
    public Map.Entry<String, Student> getItem(int pos){
        return (Map.Entry) StudentData.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.attendee_list_row, parent, false);

        TextView name_txt_v = (TextView) row.findViewById(R.id.name_txt_v);
        ImageView status_img_v = (ImageView) row.findViewById(R.id.status_img_v);

        Map.Entry<String, Student> item = getItem(pos); //get the entry

        name_txt_v.setText(item.getValue().name);       //set the name


        if(item.getValue().status){                     //set status image
            status_img_v.setBackgroundResource(R.drawable.checkboxchecked);
        } else {
            status_img_v.setBackgroundResource(R.drawable.checkbox);
        }
        return row;
    }
}