package com.irrigate.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    private static final String MIGRATION_SCRIPTS_SCHEMA_TABLE = "migration_auto_irrigate_scripts_flyway_meta_schema";
    private static final String SAMPLE_DATA_SCHEMA_TABLE = "sample_auto_irrigate_data_flyway_meta_schema";
    public static final String CLEAN_MIGRATE = "cleanMigrate";
    public static final String MIGRATE = "migrate";

    @Value("${database.db.migration.strategy}")
    private String databaseMigrationStrategy;

    @Value("${flyway.migration.sample.data.enabled}")
    private Boolean migrationSampleDataEnabled;

    @Value("${flyway.migration.sample.data.folder}")
    private String migrationSampleDataFolder;

    @Value("${flyway.migration.schema.folder}")
    private String migrationSchemaFolder;

    @Bean(name = "flywayMigrationBean")
    public Flyway flywayMigrationBean(DataSource dataSource) {
        Flyway flywayMigrationBean = new Flyway();
        flywayMigrationBean.setLocations(migrationSchemaFolder);
        flywayMigrationBean.setDataSource(dataSource);
        flywayMigrationBean.setTable(MIGRATION_SCRIPTS_SCHEMA_TABLE);
        switch (databaseMigrationStrategy) {
            case CLEAN_MIGRATE:

                flywayMigrationBean.setBaselineVersionAsString("0");
                flywayMigrationBean.clean();
                flywayMigrationBean.baseline();
                flywayMigrationBean.migrate();
                break;
            case MIGRATE:
                try{
                    flywayMigrationBean.setBaselineVersionAsString("0");
                    flywayMigrationBean.baseline();
                }catch (FlywayException e){
                    // this catch for detecting that the migration meta table already exists
                }finally {
                    flywayMigrationBean.migrate();
                }
                break;
            default:
                throw new RuntimeException("Invalid database db.development.migration strategy value " + databaseMigrationStrategy);
        }
        return flywayMigrationBean;
    }

    @Bean(name = "flywaySampleDataBean")
    @DependsOn("flywayMigrationBean")
    public Flyway flywaySampleDataBean(DataSource dataSource) {
        Flyway flywayMigrationBean = new Flyway();
        flywayMigrationBean.setLocations(migrationSampleDataFolder);
        flywayMigrationBean.setDataSource(dataSource);
        flywayMigrationBean.setTable(SAMPLE_DATA_SCHEMA_TABLE);
        if (migrationSampleDataEnabled) {
            switch (databaseMigrationStrategy) {
                case CLEAN_MIGRATE:
                    flywayMigrationBean.setBaselineVersionAsString("0");
                    flywayMigrationBean.baseline();
                    flywayMigrationBean.migrate();
                    break;
                case MIGRATE:
                    try{
                        flywayMigrationBean.setBaselineVersionAsString("0");
                        flywayMigrationBean.baseline();
                    }catch (FlywayException e){
                        // this catch for detecting that the migration meta table already exists
                    }finally {
                        flywayMigrationBean.migrate();
                    }
                    break;
                default:
                    throw new RuntimeException("Invalid database migration strategy value " + databaseMigrationStrategy);
            }
        }
        return flywayMigrationBean;
    }

}
