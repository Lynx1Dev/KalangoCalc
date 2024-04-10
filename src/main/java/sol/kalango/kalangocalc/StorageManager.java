package sol.kalango.kalangocalc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
	private static final String FILE_NAME = "combustiveis.json";
	private static final String USER_DOCUMENTS_FOLDER = System.getProperty("user.home") + File.separator + "Documents";
	private static final Path FILE_PATH = Paths.get(USER_DOCUMENTS_FOLDER, FILE_NAME);
	private static final Gson gson = new Gson();

	static {
		// Ensure the file exists, copy it from resources if necessary
		try {
			if (!Files.exists(FILE_PATH)) {
				try (InputStream inputStream = StorageManager.class.getResourceAsStream("/sol/kalango/kalangocalc/" + FILE_NAME);
					 OutputStream outputStream = new FileOutputStream(FILE_PATH.toFile())) {
					if (inputStream == null) {
						throw new IllegalStateException("Resource file not found: " + FILE_NAME);
					}

					byte[] buffer = new byte[1024];
					int length;
					while ((length = inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, length);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveCombustiveis(List<Combustivel> combustiveis) {
		try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(combustiveis, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Combustivel> loadCombustiveis() {
		try (FileReader reader = new FileReader(FILE_PATH.toFile())) {
			Type listOfCombustiveisType = new TypeToken<ArrayList<Combustivel>>() {}.getType();
			return gson.fromJson(reader, listOfCombustiveisType);
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
