//In this code I chose to manipulate time separately as hours and minutes as opposed to total minutes and later conversions
package hw2;
import java.lang.String;
public class ClockRadio {
	/**
	 * This is the constant 9, the number of minutes a snooze delays an alarm.
	 */
	public static final int SNOOZE_MINUTES = 9;
	/**
	 * This is the constant 1440, the number of minutes in a day.
	 */
	public static final int MINUTES_PER_DAY = 1440;
	/**
	 * This is the number of minutes on the clock
	 */
	private int clockMinutes;
	/**
	 * This is the number of hours on the clock
	 */
	private int clockHours;
	/**
	 * This is the number of minutes on the alarm
	 */
	private int alarmMinutes;
	/**
	 * This is the number of hours on the alarm
	 */
	private int alarmHours;
	/**
	 * This is the number of minutes on the effective alarm time
	 */
	private int effMinutes;
	/**
	 * This is the number of hours on the effective alarm time
	 */
	private int effHours;
	/**
	 * This is true if the alarm is disabled, and false if enabled
	 */
	private boolean alarmIsDisabled;
	/**
	 * This is true if the alarm is sound, and false if it is not
	 */
	private boolean alarmIsSounding;
	/**
	 * This is true if the clock is in 24-hr mode, and false if it is in 12-hr mode
	 */
	private boolean militaryMode;
	/**
	 * This is true if the radio will play as the alarm sound, and false if the alarm will buzz instead
	 */
	private boolean radioMode;
	/**
	 * This is true if snooze is active
	 */
	private boolean isSnoozed;
	
	/**
	 * Constructs a clock radio with initial clock time at 00:00 and alarm time at 01:00. Initially the alarm is disabled, the alarm is not sounding, the display is in 24-hour mode, and radio mode is off.
	 * 
	 * 
	 */
	public ClockRadio()
	{
		clockMinutes = 0;
		clockHours = 0;
		alarmMinutes = 0;
		alarmHours = 1;
		effMinutes = 0;
		effHours = 1;
		alarmIsDisabled = true;
		alarmIsSounding = false;
		militaryMode = true;
		radioMode = false;
		isSnoozed = false;
	}
	
	/**
	 * Constructs a clock radio with the given initial clock time and with alarm time at 01:00. Initially the alarm is disabled, the alarm is not sounding, the display is in 24-hour mode, and radio mode is off. The argument is assumed to be non-negative but may be arbitrarily large.
	 * @param givenMinutesPastMidnight
	 * These are the number of minutes past midnight for the clock's starting time
	 */
	public ClockRadio(int givenMinutesPastMidnight)
	{
		int minutes = givenMinutesPastMidnight % MINUTES_PER_DAY;
		int hours = minutes/60;
		int remainder = minutes % 60;
		clockMinutes = remainder;
		clockHours = hours;
		alarmMinutes = 0;
		alarmHours = 1;
		effMinutes = 0;
		effHours = 1;
		alarmIsDisabled = true;
		alarmIsSounding = false;
		militaryMode = true;
		radioMode = false;
		isSnoozed = false;
	}
	
	/**
	 * Advances the clock time by the specified number of minutes. If the updated clock time passes or equals the effective alarm time, and the alarm is on, then the clock should go into sounding mode. If snooze is not in effect, "effective alarm time" is just the alarm time as currently set. If snooze is in effect, the "effective alarm time" is 9 minutes after the clock time at which the snooze() method was last called. If the clock goes into sounding mode as a result of advancing the time, snooze is canceled. The argument must be a non-negative number but may be arbitrarily large.
	 * @param givenMinutes
	 * These are the minutes that the clock advances
	 */
	public void advanceTime(int givenMinutes)
	{
		int prevTotalMin = clockHours*60+clockMinutes;
		int totalAlarmMin = effHours*60+effMinutes;
		int dif = totalAlarmMin - prevTotalMin;
		int add = dif + MINUTES_PER_DAY;
		
		
		
		int minutes = givenMinutes % MINUTES_PER_DAY;
		int hours = minutes/60;
		
		
		int newClockMinutes = clockMinutes + (minutes % 60);
		int newClockHours = (clockHours + hours) % 24;
		if(newClockMinutes > 60)
		{
			newClockMinutes %= 60;
			newClockHours = (++newClockHours) % 24;
		}
		
		clockMinutes = newClockMinutes;
		clockHours = newClockHours;
		
	
		
		if(!alarmIsDisabled && givenMinutes >= dif && dif > 0)
		{
				alarmIsSounding = true;
		}
		else if(!alarmIsDisabled && givenMinutes >= add)
		{
			alarmIsSounding = true;
		}
		else
		{
			alarmIsSounding = false;
		}
	}
	/**
	 * Advances the clock time by the given hours and minutes. If the updated clock time passes or equals the effective alarm time, and the alarm is set, then the clock should go into ringing mode. If snooze is not in effect, "effective alarm time" is just the alarm time as currently set. If snooze is in effect, the "effective alarm time" is 9 minutes after the clock time at which the snooze() method was called. If the clock goes into ringing mode as a result of advancing the time, snooze is canceled. The arguments must be non-negative numbers but may be arbitrarily large.
	 * @param givenHours
	 * These are the hours that the clock advances
	 * @param givenMinutes
	 * These are the minutes that the clock advances
	 */
	public void advanceTime(int givenHours, int givenMinutes)
	{
		int prevTotalMin = clockHours*60+clockMinutes;
		int totalAlarmMin = effHours*60+effMinutes;
		int dif = totalAlarmMin - prevTotalMin;
		int add = dif + MINUTES_PER_DAY;
		
		int minutes = givenMinutes % MINUTES_PER_DAY;
		int hours = givenHours + minutes/60;
		
		int newClockMinutes = clockMinutes + minutes % 60;
		int newClockHours = (clockHours + hours) % 24;
		if(newClockMinutes > 60)
		{
			newClockMinutes %= 60;
			newClockHours = (++newClockHours) % 24;
		}
		
		clockMinutes = newClockMinutes;
		clockHours = newClockHours;
		
		if(!alarmIsDisabled && givenMinutes >= dif && dif > 0)
		{
				alarmIsSounding = true;
		}
		else if(!alarmIsDisabled && givenMinutes >= add)
		{
			alarmIsSounding = true;
		}
		else
		{
			alarmIsSounding = false;
		}
	}
	
	/**
	 * Turns off the alarm, stops it from sounding (if it is sounding) and cancels snooze if it is in effect.
	 */
	public void alarmDisabled()
	{
		alarmIsDisabled = true;
		alarmIsSounding = false;
		if(isSnoozed)
		{
			effMinutes = alarmMinutes;
			effHours = alarmHours;
		}
		
		
		isSnoozed = false;
	}
	
	/**
	 * Turns the alarm on. This method does not cause the alarm to start sounding regardless of current clock time and alarm time. (If clock time and alarm time are equal, it will start sounding after time is advanced by 24 hours.)
	 */
	public void alarmEnabled()
	{
		alarmIsDisabled = false;
	}
	
	/**
	 * Returns the current alarm time as a string in one of the following forms, depending on whether the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM". If the clock is in 24-hour mode, the hours value hh is between 0 and 23, inclusive, and if not, the hours value hh is between 1 and 12, inclusive, and the appropriate string "AM" or "PM" is appended. The minutes value mm is always between 0 and 59, inclusive.
	 */
	public String getAlarmTimeAsString()
	{
		if (militaryMode)
		{
			String hour;
			String minute;
			
			hour = Integer.toString(alarmHours);
			minute = Integer.toString(alarmMinutes);
			
			if(hour.length() < 2) 
				hour = "0" + hour;
			if(minute.length() < 2) 
				minute = "0" + minute;

			return hour + ":" + minute;
			
		}
		else
		{
			int hourSub;
			String AmPm;
			
			if(alarmHours > 12)
			{
				hourSub = alarmHours-12;
				AmPm = "PM";
			}
			else
			{
				hourSub = alarmHours;
				AmPm = "AM";
			}
			String hour = Integer.toString(hourSub);
			String minute = Integer.toString(alarmMinutes);
			
			if(hour.length() < 2) 
				hour = "0" + hour;
			if(minute.length() < 2) 
				minute = "0" + minute;
			
			return hour + ":" + minute + " " + AmPm;
		}
		
	}
	
	/**
	 * Returns the current alarm time as the number of minutes past midnight. This value is always between 0 and 1439, inclusive.
	 */
	public int getAlarmTimeRaw()
	{
		return (alarmHours*60+alarmMinutes) % MINUTES_PER_DAY;
		//with extra remainder check just in case
	}
	
	/**
	 * Returns the current clock time as a string in one of the following forms, depending on whether the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM". If the clock is in 24-hour mode, the hours value hh is between 0 and 23, inclusive, and if not, the hours value hh is between 1 and 12, inclusive, and the appropriate string "AM" or "PM" is appended. The minutes value mm is always between 0 and 59, inclusive.
	 */
	public String getClockTimeAsString()
	{
		if (militaryMode)
		{
			String hour;
			String minute;
			
			hour = Integer.toString(clockHours);
			minute = Integer.toString(clockMinutes);
			
			if(hour.length() < 2) 
				hour = "0" + hour;
			if(minute.length() < 2) 
				minute = "0" + minute;

			return hour + ":" + minute;
			
		}
		else
		{
			int hourSub;
			String AmPm;
			
			if(clockHours == 0)
				clockHours = 12;
			
			if(clockHours > 12)
			{
				hourSub = clockHours-12;
				AmPm = "PM";
			}
			else
			{
				hourSub = clockHours;
				AmPm = "AM";
			}
			String hour = Integer.toString(hourSub);
			String minute = Integer.toString(clockMinutes);
			
			if(hour.length() < 2) 
				hour = "0" + hour;
			if(minute.length() < 2) 
				minute = "0" + minute;
			
			return hour + ":" + minute + " " + AmPm;
		}
	}
	
	/**
	 * Returns the current clock time as the number of minutes past midnight. This value is always between 0 and 1439, inclusive.
	 */
	public int getClockTimeRaw()
	{
		return (clockHours*60+clockMinutes) % MINUTES_PER_DAY;
		//with extra remainder check just in case
	}
	
	/**
	 * Returns the current effective alarm time as a string in one of the following forms, depending on whether the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM". If the clock is in 24-hour mode, the hours value hh is between 0 and 23, inclusive, and if not, the hours value hh is between 1 and 12, inclusive, and the appropriate string "AM" or "PM" is appended. The minutes value mm is always between 0 and 59, inclusive.
	 */
	public String getEffectiveAlarmTimeAsString()
	{
		if (militaryMode)
		{
			String hour;
			String minute;
			
			hour = Integer.toString(effHours);
			minute = Integer.toString(effMinutes);
			
			if(hour.length() < 2) 
				hour = "0" + hour;
			if(minute.length() < 2) 
				minute = "0" + minute;

			return hour + ":" + minute;
		}
		else
		{
			int hourSub;
			String AmPm;
			
			if(clockHours > 12)
			{
				hourSub = effHours-12;
				AmPm = "PM";
			}
			else
			{
				hourSub = effHours;
				AmPm = "AM";
			}
			String hour = Integer.toString(hourSub);
			String minute = Integer.toString(effMinutes);
			
			if(hour.length() < 2) 
				hour = "0" + hour;
			if(minute.length() < 2) 
				minute = "0" + minute;
			
			return hour + ":" + minute + " " + AmPm;
		}
	}
	
	/**
	 * Returns the effective alarm time as the number of minutes past midnight. If snooze is not in effect, this value is the same as the current alarm time. This value is always between 0 and 1439, inclusive.
	 */
	public int getEffectiveAlarmTimeRaw()
	{
		return (effHours*60 + effMinutes) % MINUTES_PER_DAY;
		//with extra remainder check just in case
	}
	
	/**
	 * Determines whether the alarm is currently buzzing.
	 */
	public boolean isBuzzing()
	{
		return !radioMode;
	}
	
	/**
	 * Determines whether the alarm is currently playing the radio.
	 */
	public boolean isPlayingRadio()
	{
		return radioMode;
	}
	
	/**
	 * Determines whether the alarm is currently sounding (buzzer or radio).
	 */
	public boolean isSounding()
	{
		return alarmIsSounding;
	}
	
	/**
	 * Sets whether the clock should display time strings using 24-hour mode (i.e. 00:00 to 23:59) or AM/PM mode (i.e. 12:00 AM to 11:59 PM).
	 * @param use24HourDisplay
	 * This is true if the clock is in 24 hour mode, and otherwise false.
	 */
	public void set24HourDisplay(boolean use24HourDisplay)
	{
		militaryMode = use24HourDisplay;
	}
	
	/**
	 * Sets the alarm time to the given number of minutes past midnight. The hours and minutes are assumed to be non-negative but may be arbitrarily large. This method will not cause the clock to start sounding, and will not cause it to stop sounding if it is already in the sounding state. If snooze is in effect, it is canceled by this method.
	 * @param givenMinutesPastMidnight
	 * These are the number of minutes past midnight that the alarm clock will be set to.
	 */
	public void setAlarmTime(int givenMinutesPastMidnight)
	{
		int minutes = givenMinutesPastMidnight % MINUTES_PER_DAY;
		alarmHours = minutes / 60;
		alarmMinutes = minutes % 60;
		effHours = alarmHours;
		effMinutes = alarmMinutes;
		
		if(isSnoozed)
		{
			isSnoozed = false;
			effHours = alarmHours;
			effMinutes = alarmMinutes;
		}
	}
	
	/**
	 * Sets the alarm time to the given hours and minutes. The hours are assumed to be in the range [1, 12] and minutes are assumed to be in the range [0, 59]. The given time is interpreted as AM or PM based on the third argument, regardless of whether the clock is currently in 24-hour mode. This method will not cause the clock to start sounding, and will not cause it to stop sounding if it is already in the sounding state. If snooze is in effect, it is canceled by this method.
	 * @param givenHours
	 * Hour for alarm time
	 * @param givenMinutes
	 * Minute for alarm time
	 * @param isPm
	 * This is true if the time should be interpreted as PM, and false if AM.
	 */
	public void setAlarmTime(int givenHours, int givenMinutes, boolean isPm)
	{				
		if(isPm)
			if(givenHours == 12)
				alarmHours = givenHours;
			else
				alarmHours = givenHours + 12;
		else
			if(givenHours == 12)
				alarmHours = 0;
			else
				alarmHours = givenHours;
		
		alarmMinutes = givenMinutes;
		effHours = alarmHours;
		effMinutes = alarmMinutes;
		
		if(isSnoozed)
		{
			isSnoozed = false;
			effHours = alarmHours;
			effMinutes = alarmMinutes;
		}
	}
	
	/**
	 * Sets whether the clock should play the radio or the buzzer when sounding the alarm.
	 * @param useRadio
	 * This is true if the clock should play the radio when the alarm is sounding and false otherwise.
	 */
	public void setRadioMode(boolean useRadio)
	{
		radioMode = useRadio;
	}

	/**
	 * Sets the current clock time to the given number of minutes past midnight. The argument is assumed to be non-negative but may be arbitrarily large. This method will not cause the clock to start sounding, even if the alarm is currently set, and will not cause it to stop ringing if it is already in the ringing state. If snooze is in effect, it is canceled by this method.
	 * @param givenMinutesPastMidnight
	 * These are the minutes past midnight that the clock time will be set to.
	 */
	public void	setTime(int givenMinutesPastMidnight)
	{
		int minutes = givenMinutesPastMidnight % MINUTES_PER_DAY;
		clockHours = minutes / 60;
		clockMinutes = minutes % 60;
		
		if(isSnoozed)
		{
			isSnoozed = false;
			effHours = alarmHours;
			effMinutes = alarmMinutes;
		}
	}

	/**
	 * Sets the current clock time to the given hours and minutes. The hours are assumed to be in the range [1, 12] and minutes are assumed to be in the range [0, 59]. The given time is interpreted as AM or PM based on the third argument, regardless of whether the clock is currently in 24-hour mode. This method will not cause the clock to start sounding, even if the alarm is currently set, and will not cause it to stop ringing if it is already in the ringing state. If snooze is in effect, it is canceled by this method.
	 * @param givenHours
	 * Hour for clock Time
	 * @param givenMinutes
	 * Minute for clock time
	 * @param isPm
	 * This is true if the time should be interpreted as PM, and false if AM.
	 */
	public void setTime(int givenHours, int givenMinutes, boolean isPm)
	{
		if(isPm)
			if(givenHours == 12)
				clockHours = givenHours;
			else
				clockHours = givenHours + 12;
		else
			if(givenHours == 12)
				clockHours = 0;
			else
				clockHours = givenHours;
		
		clockMinutes = givenMinutes;
		
		if(isSnoozed)
		{
			isSnoozed = false;
			effHours = alarmHours;
			effMinutes = alarmMinutes;
		}
	}

	/**
	 * Stops the clock from sounding and sets the effective alarm time for SNOOZE_MINUTES minutes after the current clock time. Does not disable the alarm or change the alarm time. Does nothing if the alarm is not currently sounding.
	 */
	public void snooze()
	{
		if(alarmIsSounding)
		{
			alarmIsSounding = false;
			isSnoozed = true;
			effHours = clockHours;
			effMinutes = clockMinutes + SNOOZE_MINUTES;
			
			if(effMinutes >= 60)
			{
				effHours += effMinutes/60;
				effMinutes %= 60;
			}
		}
	}
	
}
