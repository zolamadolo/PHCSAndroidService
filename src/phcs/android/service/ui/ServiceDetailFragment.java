package phcs.android.service.ui;

import phcs.android.service.background.BackgroundService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * A fragment representing a single Service detail screen. This fragment is
 * either contained in a {@link ServiceListActivity} in two-pane mode (on
 * tablets) or a {@link ServiceDetailActivity} on handsets.
 */
public class ServiceDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";
	

	/**
	 * The dummy content this fragment is presenting.
	 */
	private ServiceContent.UIItem mItem;
	private ServiceListActivity mActivity;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ServiceDetailFragment() {
	}
	public void setActivity(ServiceListActivity activity)
	{
		mActivity = activity;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the ui content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = ServiceContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_service_detail,
				container, false);
		
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			if(!(mItem._listTitle.equalsIgnoreCase("PHCS Setting")))
			{
				rootView = inflater.inflate(R.layout.fragment_service_toggle,
						container, false);
				((TextView) rootView.findViewById(R.id.service_detail_toggle))
				.setText(mItem._detailTitle);
				
				ToggleButton toggle = (ToggleButton)rootView.findViewById(R.id.toggle_button);
				if(mActivity!=null)
				{
					toggle.setChecked(mActivity.TOGGLE_BUTTON_STATE);	
				}
				toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					   if(isChecked)
					   {
						   if(mActivity!=null)
						   {
							   mActivity.TOGGLE_BUTTON_STATE = true;
						   }
					   }else
					   {
						   if(mActivity!=null)
						   {
						   	   mActivity.TOGGLE_BUTTON_STATE = false;
						   }
					   }
						
					}
				});
				
			}else
			{
				((TextView) rootView.findViewById(R.id.service_detail))
				.setText(mItem._detailTitle);
				final Button right = (Button)rootView.findViewById(R.id.right_button);
				final Button left = (Button)rootView.findViewById(R.id.left_button);
				if(mActivity!=null)
				{
					left.setEnabled(!mActivity.SERVICE_RUNNING);
				}
				right.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						mActivity.SERVICE_RUNNING = false;
						getActivity().stopService(new Intent(getActivity(),BackgroundService.class));
						left.setEnabled(true);
					}
				});
				left.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						mActivity.SERVICE_RUNNING = true;
						getActivity().startService(new Intent(getActivity(),BackgroundService.class));
						left.setEnabled(false);
					}
				});
			}
		}
		return rootView;
	}
}
