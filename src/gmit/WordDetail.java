package gmit;

import java.util.List;

public class WordDetail {
	private List<String> definitions;

	public WordDetail(List<String> definitions) {
		this.definitions = definitions;
	}
	
	public List<String> getDefinition(){
		return definitions;
	}
	
	public void addEntry(String newEntry){
		definitions.add(newEntry);
	}

	@Override
	public String toString(){
		return ""+definitions;
	}
	
}
