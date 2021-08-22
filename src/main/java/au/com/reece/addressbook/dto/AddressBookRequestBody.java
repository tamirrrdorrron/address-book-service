package au.com.reece.addressbook.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class AddressBookRequestBody {

    @NotNull
    @NotEmpty
    @Size(max=200)
    private String name;

    @NotNull
    @NotEmpty
    private String branchNumber;
}
