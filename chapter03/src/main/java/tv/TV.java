package tv;

public class TV {
	private int channel;	// 1 ~ 255
	private int volume;		// 0 ~ 100
	private boolean power;
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}

	public void power(boolean on) {
		power = on;
	}
	
	public void channel(boolean up) {
		if(up) {
			if(channel == 255) channel = 1;
			else channel++;
		}
		else {
			if(channel == 0) channel = 255;
			else channel--;;
		}
	}
	
	public void channel(int channel) {
		if(channel > 255) 
			this.channel = 1;
		else if(channel == 0)
			this.channel = 255;
	}
	
	public void volume(boolean up) {
		if(up)
			this.volume = (volume+1)%101;
		else {
			if(volume == 0) volume = 100;
			else volume--;
		}
	}

	public void volume(int volume) {
		if(volume > 100) 
			this.volume = 0;
	}
	
	public void status() {
		System.out.println("TV[power=" + (power?"on":"off") + ", channel=" + channel + ", volume=" + volume + "]");
	}
}
