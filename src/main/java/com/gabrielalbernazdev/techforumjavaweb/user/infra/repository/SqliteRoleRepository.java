package com.gabrielalbernazdev.techforumjavaweb.user.infra.repository;

import com.gabrielalbernazdev.techforumjavaweb.user.domain.model.Role;
import com.gabrielalbernazdev.techforumjavaweb.user.domain.repository.RoleRepository;
import com.gabrielalbernazdev.techforumjavaweb.util.database.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class SqliteRoleRepository implements RoleRepository {
    @Override
    public Set<Role> findRolesByNames(Connection connection, Set<Role> roles) throws SQLException {
        if (roles == null || roles.isEmpty()) {
            return roles;
        }

        Set<String> rolesNames = roles.stream()
                    .map(Role::getName)
                    .map(String::trim)
                    .collect(Collectors.toSet());

        String placeholders = String.join(", ", Collections.nCopies(rolesNames.size(), "?"));

        String sql = "SELECT id, name FROM " + Table.ROLES + " WHERE name IN (" + placeholders + ")";
        Set<Role> rolesResult = new HashSet<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int index = 1;
            for (String name : rolesNames) {
                pstmt.setString(index++, name);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    rolesResult.add(new Role(UUID.fromString(id), name));
                }
            }
        }

        return rolesResult;
    }
}
