package seedu.moneygowhere.logic.commands;

import static seedu.moneygowhere.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.moneygowhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.moneygowhere.testutil.TypicalSpendings.getTypicalSpendingBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.moneygowhere.model.Model;
import seedu.moneygowhere.model.ModelManager;
import seedu.moneygowhere.model.UserPrefs;
import seedu.moneygowhere.model.spending.Spending;
import seedu.moneygowhere.testutil.SpendingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSpendingBook(), new UserPrefs());
    }

    @Test
    public void execute_newSpending_success() {
        Spending validSpending = new SpendingBuilder().build();

        Model expectedModel = new ModelManager(model.getSpendingBook(), new UserPrefs());
        expectedModel.addSpending(validSpending);

        assertCommandSuccess(new AddCommand(validSpending), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validSpending), expectedModel);
    }

    @Test
    public void execute_duplicateSpending_throwsCommandException() {
        Spending spendingInList = model.getSpendingBook().getSpendingList().get(0);
        assertCommandFailure(new AddCommand(spendingInList), model, AddCommand.MESSAGE_DUPLICATE_SPENDING);
    }

}
