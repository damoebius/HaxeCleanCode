import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.apache.log4j.Logger;
import org.tamina.intellij.codecleaner.Parser;
import org.tamina.intellij.codecleaner.entry.ClassContentsEntry;
import org.tamina.intellij.codecleaner.settings.RearrangerSettings;
import org.tamina.intellij.codecleaner.util.CommentUtil;

import java.util.List;

/**
 * Created by damo on 03/03/17.
 */
public class CleanAction extends AnAction {

    private static final Logger logger = Logger.getLogger("CleanAction");

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        final Document    document    = editor.getDocument();
        final PsiFile     psiFile     = event.getData(PlatformDataKeys.PSI_FILE);
        rearrangeDocument(project,psiFile,document);
    }

    public final void rearrangeDocument(final Project            project ,
                                        final PsiFile psiFile ,
                                        final Document document )
    {
        logger.debug("enter rearrangeDocument");
        final RearrangerSettings settings = RearrangerSettings.getDefaultSettings();
        CommentUtil u = new CommentUtil(settings);
        final Parser p = new Parser(project, settings, psiFile);
        final List<ClassContentsEntry> outerClasses           = p.parseOuterLevel();
        /*if (outerClasses.size() > 0)
        {
            final Mover     m                      = new Mover(outerClasses, settings);
            final List<IRuleInstance> resultRuleInstances = m.rearrangeOuterClasses();
            boolean rearrange = true;
            if (settings.isAskBeforeRearranging()) {
                FileStructurePopup fsp = new FileStructurePopup(settings, resultRuleInstances, psiFile);
                rearrange = fsp.displayRearrangement();
            }
            if (rearrange) {
                final Emitter   e                      = new Emitter(psiFile, resultRuleInstances, document);
                e.emitRearrangedDocument();
            }
        }
        logger.debug("respacing document");
        PsiDocumentManager.getInstance(project).commitDocument(document);
        Spacer spacer = new Spacer(project, psiFile, document, settings);
        if (spacer.respace()) {
            PsiDocumentManager.getInstance(project).commitDocument(document);
        }  */
        logger.debug("exit rearrangeDocument");
    }

}
