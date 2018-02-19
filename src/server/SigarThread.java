package server;

import java.util.Random;

import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SigarThread extends Thread {
	protected static final long TOTAL_TIME_UPDATE_LIMIT = 1000l;
	Sigar sigar;
	int cpuCount;
	long pid;
	ProcCpu prevPc;
	double load;
	Random rand;
	int i=0;
	
	ServerFXController controller = null;
	
	public SigarThread(ServerFXController controller) throws SigarException {
//		sigar = new Sigar();
//		cpuCount = sigar.getCpuList().length;
//		pid = sigar.getPid();
//		prevPc = sigar.getProcCpu(pid);
//		load = 0;
//		new Timer(true).schedule(updateLoadTask, 1000l);
		this.controller = controller;
		i=0;
		rand = new Random();
	}
	
	@Override
	public void run() {
		while (true) {
			//	            ProcCpu curPc = sigar.getProcCpu(pid);
//	            long totalDelta = curPc.getTotal() - prevPc.getTotal();
//	            long timeDelta = curPc.getLastTime() - prevPc.getLastTime();
//	            if (totalDelta == 0) {
//	                if (timeDelta > TOTAL_TIME_UPDATE_LIMIT) load = 0;
//	                if (load == 0) prevPc = curPc;
//	            } else {
//	                load = 100. * totalDelta / timeDelta / cpuCount;
//	                prevPc = curPc;
//	            }
			//System.out.println(controller);
			controller.setStatusUser(i++);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public double getLoad() {
        return load;
    }
}
