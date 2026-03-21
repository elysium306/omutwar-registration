package com.omutwar.registration.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dbtest")
public class DatabaseTestController {

	private final JdbcTemplate jdbc;

	public DatabaseTestController(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@GetMapping("/create")
	public String createTable() {
		jdbc.execute("""
				    CREATE TABLE IF NOT EXISTS test_items (
				        id SERIAL PRIMARY KEY,
				        name TEXT NOT NULL,
				        created_at TIMESTAMP DEFAULT NOW()
				    )
				""");
		return "Table created";
	}

	@PostMapping("/insert")
	public String insert(@RequestParam String name) {
		jdbc.update("INSERT INTO test_items (name) VALUES (?)", name);
		return "Inserted: " + name;
	}

	@GetMapping("/list")
	public List<Map<String, Object>> list() {
		return jdbc.queryForList("SELECT * FROM test_items ORDER BY id");
	}

	@DeleteMapping("/clear")
	public String clear() {
		jdbc.update("DELETE FROM test_items");
		return "All rows deleted";
	}
}