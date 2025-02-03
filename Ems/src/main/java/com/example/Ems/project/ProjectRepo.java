package com.example.Ems.project;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface ProjectRepo  extends JpaAttributeConverter< Project, Integer> {
}
