package com.code.spring.ioc.dependency.injection;

import com.code.spring.ioc.dependency.DependencyUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 愆凡
 * @date 2021/2/4 21:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DependencyUserHolder {

	private DependencyUser user;

}
