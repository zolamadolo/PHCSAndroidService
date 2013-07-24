/**
 *  Copyright 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/)
 *	This work is licenced under the Creative Commons Attribution 2.5 South Africa License. 
 *	To view a copy of this licence, visit http://creativecommons.org/licenses/by/2.5/za/ or 
 *	send a letter to Creative Commons, 171 Second Street, 
 *	Suite 300, San Francisco, California 94105, USA.
 *
 *  Visit http://www.rcips.uct.ac.za/ip/copyright/bla/
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*******************************************************************************
 * Copyright (c) 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/).
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package phcs.android.service.background;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @author Zola Madolo
 *
 */
public class BackgroundService extends Service {
	
	private ServiceThread mServiceThread;
	//private NotificationManager mNotificationManager;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	/**
	 * Start ServiceThread to doBackground work... create Client... and Bluetooth Socket...
	 * @param intent
	 */
	private void forkService(Intent intent)
	{
		mServiceThread = new ServiceThread();
		Thread thread = new Thread(mServiceThread);
		thread.start();
	}
	/* Call when instantiating a Service
	 * create NotificationManager and pass it to ServiceThread...
	 */
	@Override
	public void onCreate() {
		//mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	}
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		Toast.makeText(getApplicationContext(),"Started",Toast.LENGTH_LONG).show();
		this.forkService(intent);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		this.forkService(intent);
		Toast.makeText(getApplicationContext(),"Started",Toast.LENGTH_LONG).show();
		return START_STICKY;
	}
}
