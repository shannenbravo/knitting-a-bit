//Pattern Array List, make sure it's member data
ArrayList<Pattern> patterns = new ArrayList<Pattern>;

//Alphabetical name sort for Pattern
Collections.sort(patterns, new Comparator<Pattern>(){
	public int compare(Pattern a, Pattern b){
			return a.getName().compareTo(b.getName());
	}
});

//Project Array List, make sure it's member data
ArrayList<Project> projects = new ArrayList<Project>;

//Alphabetical name sort for Project
Collections.sort(projects, new Comparator<Project>(){
	public int compare(Project a, Project b){
			return a.getName().compareTo(b.getName());
	}
});

//Parcel passing
Section dataToSend = new Section();
Intent i = new Intent(this, NewActivity.class);
i.putExtra("Section", dataToSend); // using the (String name, Parcelable value) overload!
startActivity(i); // dataToSend is now passed to the new Activity

//Parcel Recieving, in on create I think? Dunno
MyParcelable object = (MyParcelable) getIntent().getParcelableExtra("myData");